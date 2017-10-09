node('docker') {
  stage('checkout'){
      checkout scm
  }
  stage('build') {
    sh '''
        #/docker.sh run --rm -u $(id -u) -v $(pwd):/src -w /src java:jdk ./mvnw clean verify
        ./mvnw clean verify
        /docker.sh build -f Dockerfile-nonjdk -t test . 
        #/docker.sh build -t test . 
        
    '''
  }
}
