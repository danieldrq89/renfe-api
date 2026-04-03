pipeline {
    agent any
    options {
        skipDefaultCheckout()
    }
    environment {
        DOCKER_USER = 'danidrq89'
        DOCKER_CREDS = credentials('docker-hub-creds')
    }

    stages {
        stage('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
        }

        stage('Docker Build, Tag & Push') {
            steps {
                script {
                    def tagVersion = "v1_${sh(script: 'date +%d_%m_%Y', returnStdout: true).trim()}__${env.BUILD_NUMBER}"

                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-creds') {

                        def apiImage = docker.build("${DOCKER_USER}/renfe-api:latest", ".")
                        apiImage.push('latest')
                        apiImage.push(tagVersion)

                        def dbImage = docker.build("${DOCKER_USER}/renfe:latest", "./docker-database")
                        dbImage.push('latest')
                        dbImage.push(tagVersion)
                    }
                }
            }
        }
    }
}