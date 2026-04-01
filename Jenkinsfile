pipeline {
    agent {docker {
    image 'maven:3.8.4-openjdk-17-slim '
    }}

    stages {
        stage('Test') {
            steps {
                echo 'Hello Tests'
            }
        }
        stage('Build') {
            steps {
                sh "mvn --version"
            }
        }
        stage('QA') {
            steps {
                echo 'Hello QA'
            }
        }
    }

    post {
        always {
            echo 'Always running...'
        }
        success {
            echo 'Success!'
        }
        failure {
            echo 'Failure'
        }
    }
}