podTemplate(label: 'buildPod',
    volumes: [
        hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
        secretVolume(secretName: 'registry-account', mountPath: '/var/run/secrets/registry-account'),
        configMapVolume(configMapName: 'registry-config', mountPath: '/var/run/configs/registry-config')
    ],
    containers: [
        containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'docker' , image: 'docker:17.06.1-ce', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'java', image: 'java:8-jdk-alpine', ttyEnabled: true, command: 'cat')
  ]) {

    node('buildPod') {
        checkout scm
        container('java') {
          stage('Build jar file') {
            sh """
            #!/bin/bash
            ./mvnw clean verify
            """
            stash(name: 'jar', includes: 'target/demo-0.0.1-SNAPSHOT.jar')
          }
        }
        container('docker') {
            stage('Build Docker Image') {
                sh """
                #!/bin/bash
                ls -lah
                ls -lah target/
                """
                unstash(name: 'jar')
                sh """
                #!/bin/bash
                NAMESPACE=`cat /var/run/configs/registry-config/namespace`
                REGISTRY=`cat /var/run/configs/registry-config/registry`
                docker build -t \${REGISTRY}/\${NAMESPACE}/boot-hello-world:${env.BUILD_NUMBER} -f Dockerfile-nonjdk .
                """
            }
            stage('Push Docker Image to Registry') {
                sh """
                #!/bin/bash
                NAMESPACE=`cat /var/run/configs/registry-config/namespace`
                REGISTRY=`cat /var/run/configs/registry-config/registry`
                set +x
                DOCKER_USER=`cat /var/run/secrets/registry-account/username`
                DOCKER_PASSWORD=`cat /var/run/secrets/registry-account/password`
                docker login -u=\${DOCKER_USER} -p=\${DOCKER_PASSWORD} \${REGISTRY}
                set -x
                docker push \${REGISTRY}/\${NAMESPACE}/boot-hello-world:${env.BUILD_NUMBER}
                """
            }
        }
        container('kubectl') {
            stage('Deploy new Docker Image') {
                sh """
                #!/bin/bash
                set +e
                NAMESPACE=`cat /var/run/configs/registry-config/namespace`
                REGISTRY=`cat /var/run/configs/registry-config/registry`
                DEPLOYMENT=`kubectl get deployments | grep boot-hello-world awk '{print \$1}'`
                kubectl get deployments \${DEPLOYMENT}
                if [ \${?} -ne "0" ]; then
                    # No deployment to update
                    echo 'No deployment to update'
                    exit 1
                fi
                # Update Deployment
                kubectl set image deployment/\${DEPLOYMENT} boot-hello-world=\${REGISTRY}/\${NAMESPACE}/boot-hello-world:${env.BUILD_NUMBER}
                kubectl rollout status deployment/\${DEPLOYMENT}
                """
            }
        }
    }
}