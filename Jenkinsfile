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
                script{ 
                        sh 'dotnet build --configuration Release'
                }
            }
        }
        stage('Test') {
            steps {
                script{
                    'dotnet test --configuration Release --no-build'
                } 
            }
        }

        triggers{
            pollSCM('H/5 * * * *')
        }
    }
}
