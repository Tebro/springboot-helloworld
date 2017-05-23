node('maven') {
  stage('checkout'){
      checkout scm
  }
  stage('build') {
    sh "mvn clean verify"
    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
  }
}
