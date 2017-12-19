env.DOCKERHUB_USERNAME = 'oketels'

pipeline {
    agent any

    triggers {
        pollSCM('*/2 * * * *')
    }

    stages {
        stage('Build') {
            agent {
                docker 'java:8-jdk'
                args '-v $HOME/.m2:/root/.m2'
            }

            steps {
                checkout scm
                sh './mvnw install dockerfile:build'

                withDockerRegistry([credentialsId: 'dockerhubcredentials']) {
                    sh "docker push ${DOCKERHUB_USERNAME}/ci-cd-demo"
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
                echo "Deploying ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
    }
}