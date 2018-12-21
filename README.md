# upstream-pipeline
CloudBees Core trial walk-thru

Destination for published pipelnes
see: https://go.cloudbees.com/docs/general/trial-guide/#_configuring_the_upstream_pipeline_to_publish_an_event

    stage('Publish Event') {
      steps {
        echo 'Publishing event: testingCompleted'
        script {
           // both options fail with No such DSL method 'simpleEvent' found, No such DSL method 'jsonEvent' found
           //publishEvent simpleEvent('testingCompleted')
           publishEvent jsonEvent('{"eventName":"testingCompleted"}')
        }
      }
    }
    
    -- or --
    -- but, cannot update in CloudBees Ops Center...
    -- see: https://go.cloudbees.com/docs/cloudbees-documentation/admin-cje/cross-team-collaboration/
    
    stage('Publish Event') {
      steps {
        echo 'Publishing event: testingCompleted'
        publishEvent simpleEvent('testingCompleted')
      }
    }
