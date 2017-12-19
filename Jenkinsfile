pipeline {
    agent any

    triggers {
        pollSCM('*/2 * * * *')
    }

    stages {
        stage('Build') {
            agent { docker 'java:8-jdk' }
            steps {
                checkout scm
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
                sh 'echo "Deploying ${env.BUILD_ID} on ${env.JENKINS_URL}"'
            }
        }
    }
}