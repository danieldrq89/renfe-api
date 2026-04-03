pipeline {
    agent any

    environment {
        DOCKER_USER = 'danidrq89'
        // Generamos un tag con fecha y numero de build: v1_03_04_2026_build5
        TAG_VERSION = "v1_${sh(script: 'date +%d_%m_%Y', returnStdout: true).trim()}__${env.BUILD_NUMBER}"
        DOCKER_CREDS = credentials('docker-hub-creds')
    }

    stages {
        stage('Docker Build & Tag') {
                    steps {
                        script {
                            // Esto usa el plugin de Docker de Jenkins
                            docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-creds') {

                                // Construye la imagen de la API
                                def apiImage = docker.build("${DOCKER_USER}/renfe-api:latest", ".")
                                apiImage.push()
                                apiImage.push("${TAG_VERSION}")

                                // Construye la imagen de la DB
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
                    
                    // Subimos la versión específica (por si acaso hay que volver atrás)
                    sh "docker push ${DOCKER_USER}/renfe-api:${TAG_VERSION}"
                    sh "docker push ${DOCKER_USER}/renfe:${TAG_VERSION}"
                    
                    // Subimos el latest (que es el que usará tu docker-compose)
                    sh "docker push ${DOCKER_USER}/renfe-api:latest"
                    sh "docker push ${DOCKER_USER}/renfe:latest"
                }
            }
        }
    }
}