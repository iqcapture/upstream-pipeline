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
        echo '(disabled) Publishing event: testingCompleted'
        //publishEvent simpleEvent('testingCompleted')
      }
    }
    stage('Cleanup') {
      steps {
        echo 'Cleanup build stage'
      }
    }
  }
}
