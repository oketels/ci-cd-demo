env.DOCKERHUB_USERNAME = 'oketels'

pipeline {
    agent any

    triggers {
        pollSCM('*/2 * * * *')
    }

    stages {
        stage('Build') {
            agent { docker 'java:8-jdk'}

            steps {
                checkout scm
                sh './mvnw install dockerfile:build'

                withDockerRegistry([credentialsId: 'dockerhubcredentials', url:'https://index.docker.io/v1/']) {
                    sh "docker push ${DOCKERHUB_USERNAME}/ci-cd-demo"
                }
            }
        }

        stage('Test') {
            agent{ node { label 'docker-prod'}}
            steps {
                echo 'Testing..'
            }
        }

        stage('Deploy'){
            agent{ node { label 'docker-prod'}}
            steps{
                echo "Deploying ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
    }
}