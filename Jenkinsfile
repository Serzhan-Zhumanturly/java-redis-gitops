pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                dir('java-app') {
                    sh 'mvn clean package -DskipTests'
                    sh 'docker build -t java-redis-app:1 .'
                }
            }
        }

        stage('Security Scan') {
            steps {
                sh '''
                    trivy image \
                      --timeout 30m \
                      --exit-code 1 \
                      --severity HIGH,CRITICAL \
                      java-redis-app:1
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline аяқталды. Build және Security Scan нәтижелерін тексеріңіз.'
        }
    }
}
