pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Hello Tests'
            }
        }
        stage('Build') {
            steps {
                echo 'Hello Builds'
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