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
    }
}