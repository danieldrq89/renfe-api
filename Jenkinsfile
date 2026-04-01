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
                sh "${tool 'Maven'}/bin/mvn --version"
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