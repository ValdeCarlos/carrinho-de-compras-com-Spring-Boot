pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Credenciais do Docker Hub
    }

    stages {
        // Stage 1: Build do projeto com Maven
        stage('Build') {
            steps {
                script {
                    echo 'Building the project...'
                    sh 'mvn clean package'
                }
            }
        }

        // Stage 2: Executar testes
        stage('Test') {
            steps {
                script {
                    echo 'Running tests...'
                    sh 'mvn test'
                }
            }
        }

        // Stage 3: Construir imagem Docker (opcional)
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    sh 'docker build -t meu-carrinho-app .'
                }
            }
        }

        // Stage 4: Publicar imagem no Docker Hub (opcional)
        stage('Push Docker Image') {
            steps {
                script {
                    echo 'Pushing Docker image to Docker Hub...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        sh 'docker tag meu-carrinho-app $DOCKER_USERNAME/meu-carrinho-app:latest'
                        sh 'docker push $DOCKER_USERNAME/meu-carrinho-app:latest'
                    }
                }
            }
        }

        // Stage 5: Deploy da aplicação
        stage('Deploy') {
            steps {
                script {
                    echo 'Deploying the application...'
                    sh 'docker-compose up -d' // Usando Docker Compose para deploy
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}