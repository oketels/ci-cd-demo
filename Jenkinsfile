pipeline {
    agent any

    triggers {
        pollSCM('*/2 * * * *')
    }

    environment{
        DOCKERHUB_USERNAME = 'oketels'
        DOCKER_HUB_CREDENTIALS = credentials('dockerhubcredentials')
    }

    stages {
        stage('Build') {
            agent{ node { label 'docker-dev'}}
            steps {
                checkout scm

                sh 'echo "username is $DOCKER_HUB_CREDENTIALS_USR and password is $DOCKER_HUB_CREDENTIALS_PSW"'
                sh './mvnw clean install'

                sh 'docker build -t ${DOCKERHUB_USERNAME}/ci-cd-demo:${BUILD_NUMBER} .'

                withDockerRegistry([url: 'https://index.docker.io/v1/', credentialsId: 'dockerhubcredentials']) {
                    sh "docker push ${DOCKERHUB_USERNAME}/ci-cd-demo:${BUILD_NUMBER}"
                }
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
                sh 'docker service update --image ${DOCKERHUB_USERNAME}/ci-cd-demo:${BUILD_NUMBER} ci-cd-demo'
            }
        }
    }
}