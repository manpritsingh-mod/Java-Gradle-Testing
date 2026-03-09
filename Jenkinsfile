pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    environment {
        REPO_URL       = 'https://github.com/manpritsingh-mod/Java-Gradle-Testing.git'
        REPO_BRANCH    = 'master'
        HEALING_WEBHOOK = 'http://healing-engine:5000/webhook/jenkins'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${env.REPO_BRANCH}", url: "${env.REPO_URL}"
            }
        }

        stage('Build') {
            steps {
                sh '''
                    chmod +x gradlew
                    ./gradlew clean build -x test
                '''
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/build/test-results/test/*.xml'
                }
            }
        }
    }

    post {
        failure {
            script {
                echo "BUILD FAILED — Sending webhook to Self-Healing Engine..."
                def payload = """{
                    "name": "${env.JOB_NAME}",
                    "build": {
                        "number": ${env.BUILD_NUMBER},
                        "status": "FAILURE",
                        "url": "${env.BUILD_URL}"
                    }
                }"""
                try {
                    httpRequest(
                        url: "${env.HEALING_WEBHOOK}",
                        httpMode: 'POST',
                        contentType: 'APPLICATION_JSON',
                        requestBody: payload,
                        validResponseCodes: '200:299'
                    )
                    echo "Webhook sent successfully!"
                } catch (Exception e) {
                    echo "Webhook failed (non-critical): ${e.message}"
                }
            }
        }
        success {
            echo "BUILD SUCCESSFUL — No healing needed."
        }
    }
}
