pipeline {
    agent any

    tools {
        maven 'maven'
    }

    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests on')
    }

    environment {
        BROWSERSTACK_USERNAME = credentials('bs_username')
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
                bat """
                    mvn clean test ^
                    -Dbrowser=${params.BROWSER} ^
                    -Dbrowserstack.user=%BROWSERSTACK_USERNAME% ^
                    -Dbrowserstack.key=%BROWSERSTACK_ACCESS_KEY%
                """
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/cucumber-reports/*,target/extent-report/*', allowEmptyArchive: true
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/extent-report',
                    reportFiles: 'ExtentHtml.html',
                    reportName: 'Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
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
