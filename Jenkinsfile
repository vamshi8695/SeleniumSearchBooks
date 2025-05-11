pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        BROWSERSTACK_USERNAME = credentials('bs_username') // Ensure this ID exists in Jenkins
        BROWSERSTACK_ACCESS_KEY = credentials('bs_access_key')
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vamshi8695/SeleniumSearchBooks.git'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dbrowser=$BROWSER'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/cucumber-reports/*', allowEmptyArchive: true
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        failure {
            echo "Build failed. Check test reports and logs."
        }
    }
}
