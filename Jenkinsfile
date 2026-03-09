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
            sh """
                echo "BUILD FAILED — Sending webhook to Self-Healing Engine..."
                curl -s -X POST \\
                    -H 'Content-Type: application/json' \\
                    -d '{"name": "${env.JOB_NAME}", "build": {"number": ${env.BUILD_NUMBER}, "status": "FAILURE", "url": "${env.BUILD_URL}"}}' \\
                    ${env.HEALING_WEBHOOK} || echo "Webhook call failed (non-critical)"
            """
        }
        success {
            echo "BUILD SUCCESSFUL — No healing needed."
        }
    }
}
