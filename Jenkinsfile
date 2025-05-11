pipeline {
    agent any

    tools {
        maven 'maven' // Use your configured Maven installation
    }

    environment {
        BROWSERSTACK_USERNAME = credentials('bs_username') // BrowserStack username from Jenkins credentials
        BROWSERSTACK_ACCESS_KEY = credentials('bs_access_key') // BrowserStack access key from Jenkins credentials
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
    }

    post {
        always {
            // ✅ Ensure test reports are parsed properly
            junit 'target/surefire-reports/*.xml'

            // (Optional) Clean workspace after build
            cleanWs()
        }

        failure {
            // (Optional) echo something or send Slack/email
            echo "Build failed. Check test reports and logs."
        }
    }
}
