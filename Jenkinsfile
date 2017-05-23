node('maven') {
  stage('checkout'){
      checkout scm
  }
  stage('build') {
    steps {
      sh "pwd"
      sh "ls -lah"
      sh "mvn clean verify"
    }
  }
}
