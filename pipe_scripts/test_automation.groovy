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
                       echo "Build the test automation project usind maven"
                       bat "mvn -f TestAutothonFramework/pom.xml clean package"
                }
            }
        }
        stage('Run Test') {
            steps {
                script {
                       echo "Skipping as of now"
                       bat "mvn -f TestAutothonFramework/pom.xml clean test -D test_file=testng.xml"
                }
            }
        }
        stage('Report') {
            steps {
                script {
                       echo "Skipping as of now"
                       archiveArtifacts artifacts: '**/test-output/', followSymlinks: false
                }
            }
        }
    }
}