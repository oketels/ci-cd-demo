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
            agent{ node { label 'master'}}
            steps {
                checkout scm

                sh 'echo "username is $DOCKER_HUB_CREDENTIALS_USR and password is $DOCKER_HUB_CREDENTIALS_PSW"'
                sh './mvnw install dockerfile:build'
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
                sh 'echo "Deploying ..."'
            }
        }
    }
}