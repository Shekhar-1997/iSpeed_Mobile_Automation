pipeline {
    agent any

    tools {
        jdk 'JDK11'
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'feature/shekhar',
                    credentialsId: 'github-pat',
                    url: 'https://github.com/Shekhar-1997/iSpeed_Mobile_Automation.git'
            }
        }

        stage('Verify Environment') {
            steps {
                bat '''
                echo =========================
                echo JAVA VERSION
                java -version

                echo =========================
                echo MAVEN VERSION
                mvn -version
                '''
            }
        }

        stage('Run TestNG Automation') {
            steps {
                bat '''
                mvn clean test -DsuiteXmlFile=resources/testCases.xml
                '''
            }
        }
    }

    post {
        always {
            publishTestNGResults testResultsPattern: '**/testng-results.xml'
        }

        success {
            echo 'Pipeline executed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}
