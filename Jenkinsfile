pipeline {
    agent any

    triggers {
        pollSCM('*/2 * * * *')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            node('docker-prod') {
                steps {
                    echo "Deploying ${env.BUILD_ID} on ${env.JENKINS_URL}"
                }
            }
        }
    }
}