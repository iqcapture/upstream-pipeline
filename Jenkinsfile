pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Build the project'
      }
    }
    stage('Testing') {
      steps {
        sleep 5
        echo 'Tests Completed!'
      }
    }
    stage('Publish Event') {
      steps {
        script {
          echo '--Publishing event goes here--'
        }

      }
    }
    stage('Cleanup') {
      steps {
        echo 'Cleanup build stage'
      }
    }
  }
}