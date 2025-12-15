pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'Maven3.9.11'
    }

    environment{
        VERSION_BACK = "2.0.1"
    }

    stages {
        stage('Checkout'){
            steps {
                git branch: 'master',
                    url: 'https://github.com/yaninajso57/ExamenMocYSO.git'
            }
        }

        stage('Build'){
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test'){
            steps {
                bat 'mvn test'
            }
        }

        stage('Packege'){
            steps {
                bat 'mvn package'
            }
        }

        stage('Move jar') {
            steps {
                bat 'echo "Eliminando directorio versiones...."'
                bat 'if exist versiones rmdir /S /Q versiones'
            }
            post {
                success {
                    bat 'echo "Se crea el directorio versiones con la última versión de la api"'
                    bat 'mkdir versiones'
                    bat 'for %f in (target\\*%VERSION_BACK%.jar) do copy "%f" versiones\\'
                }
            }

        }
    }
}