pipeline {
    agent any
    tools {
        maven 'maven_home'
        jdk 'jdk_11'
    }
    parameters {
        string(name: 'YOUTUBE_URL', defaultValue: '', description: 'Description of the parameter')
    }
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from Git
                git branch: 'pipeline_trigger', credentialsId: '446ad256-8a40-420d-acaf-20d67e4c38dd', url: 'https://github.com/arkadasgupta17/TestAutothonFramework.git'
            }
        }
        stage('Build') {
            steps {
                // Run Maven build
                sh 'mvn -f /Users/I320807/git/TestAutothonFramework/TestAutothonFramework/pom.xml clean package'
            }
        }
        stage('Run JAR') {
            steps {
                // Run the Maven-built JAR
                sh 'java -jar /Users/I320807/git/TestAutothonFramework/TestAutothonFramework/target/TestAutomatioFramework-0.0.1-SNAPSHOT-jar-with-dependencies.jar ${YOUTUBE_URL}'
            }
        }
    }
}