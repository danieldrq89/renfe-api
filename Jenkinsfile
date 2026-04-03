pipeline {
    agent any

    environment {
        DOCKER_USER = 'danidrq89'
        TAG_VERSION = "v1_${sh(script: 'date +%d_%m_%Y', returnStdout: true).trim()}__${env.BUILD_NUMBER}"
        DOCKER_CREDS = credentials('docker-hub-creds')
    }

    stages {
        stage('Docker Build & Tag') {
                    steps {
                        script {
                            docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-creds') {

                                // Construye la imagen de la API
                                def apiImage = docker.build("${DOCKER_USER}/renfe-api:latest", ".")
                                apiImage.push()
                                apiImage.push("${TAG_VERSION}")

                                def dbImage = docker.build("${DOCKER_USER}/renfe:latest", "./docker-database")
                                dbImage.push()
                                dbImage.push("${TAG_VERSION}")
                            }
                        }
                    }
                }
        stage('Push to Hub') {
            steps {
                script {
                    sh "echo $DOCKER_CREDS_PSW | docker login -u $DOCKER_CREDS_USR --password-stdin"
                    
                    sh "docker push ${DOCKER_USER}/renfe-api:${TAG_VERSION}"
                    sh "docker push ${DOCKER_USER}/renfe:${TAG_VERSION}"
                    
                    sh "docker push ${DOCKER_USER}/renfe-api:latest"
                    sh "docker push ${DOCKER_USER}/renfe:latest"
                }
            }
        }
    }
}