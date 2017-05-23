node('maven') {
  stage('checkout'){
      checkout scm
  }
  stage('build') {
    sh "mvn clean verify"
  }
}
