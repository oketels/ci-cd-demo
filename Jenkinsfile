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
            steps {
                node('docker-prod') {
                    echo "Deploying ${env.BUILD_ID} on ${env.JENKINS_URL}"
                }
            }
        }
    }
}