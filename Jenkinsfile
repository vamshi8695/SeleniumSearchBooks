pipeline {
    agent any

    tools {
        maven 'Maven 3.8.5' // use your configured Maven version
    }

    environment {
        BROWSERSTACK_USERNAME = credentials('bs_username') // Use Jenkins credentials
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
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
