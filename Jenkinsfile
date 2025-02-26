pipeline {
    agent any
    stages {
        stage('Clone Repositório') {
            steps {
                git branch: 'main', 
                    credentialsId: 'azuredevops/******', 
                    url: 'https://vadao@dev.azure.com/vadao/Teste/_git/Teste'
            }
        }
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }
        stage('Publicar Resultados dos Testes') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    sh 'scp target/meu-projeto.jar usuario@servidor:/caminho/deploy'
                }
            }
        }
    }
    triggers {
        pollSCM('H/5 * * * *')
    }
}
