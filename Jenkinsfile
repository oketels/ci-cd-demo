pipeline {
    agent any

    triggers {
        pollSCM('*/2 * * * *')
    }

    environment{
        DOCKER_HUB_CREDENTIALS = credentials('dockerhubcredentials')
    }

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh './mvnw install dockerfile:build'
                sh 'echo "username is $DOCKER_HUB_CREDENTIALS_USR and password is $DOCKER_HUB_CREDENTIALS_PSW"'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy'){
            agent{ node { label 'docker-prod'}}
            steps{
                sh 'echo "Deploying ${env.BUILD_ID} on ${env.JENKINS_URL}"'
            }
        }
    }
}