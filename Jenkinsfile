node('docker') {
  stage('checkout'){
      checkout scm
  }
  stage('build') {
    sh '''
        pwd
        ls -lah
        /docker.sh run --rm -u $(id -u) -v $(pwd):/src -w /src java:jdk ./mvnw clean verify
        /docker.sh build -f Dockerfile-nonjdk -t test . 
        
    '''
  }
}
