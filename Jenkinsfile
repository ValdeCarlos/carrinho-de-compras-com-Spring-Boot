pipeline {
    agent any

    stages {
        // Estágio 1: Clone do repositório
        stage('Clone Repositório') {
            steps {
                git branch: 'main', 
                    credentialsId: 'azuredevops/******', 
                    url: 'https://vadao@dev.azure.com/vadao/Teste/_git/Teste'
            }
        }

        // Estágio 2: Build do projeto com Maven
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        // Estágio 3: Execução dos testes
        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        // Estágio 4: Publicar resultados dos testes (opcional)
        stage('Publicar Resultados dos Testes') {
            steps {
                junit '**/target/surefire-reports/*.xml' // Publica os resultados dos testes no Jenkins
            }
        }
    }

    // Gatilho para verificar mudanças no repositório a cada 5 minutos
    triggers {
        pollSCM('H/5 * * * *')
    }
}
