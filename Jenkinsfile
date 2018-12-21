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
    stage('Cleanup') {
      steps {
        echo 'Cleanup build stage'
      }
    }
  }
}