pipeline {
    agent any
    stages {
        stage('Cloning Repository') {
            steps {
                script {
                       checkout scm 
                }
            }
        }
        
        stage('Build Project') {
            steps {
                script {
                       echo "Skipping as of now"
                }
            }
        }
        stage('Run Test') {
            steps {
                script {
                       echo "Skipping as of now"
                }
            }
        }
        stage('Report') {
            steps {
                script {
                       echo "Skipping as of now"
                }
            }
        }
    }
}