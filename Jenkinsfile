def ssh_publisher(SERVER_CONFIG) {
    sshPublisher(
        continueOnError: false,
        failOnError: true,
        publishers:[
            sshPublisherDesc(
                configName: "${SERVER_CONFIG}",
                verbose: true,
                transfers: [
                    sshTransfer(
                        sourceFiles: "target/*.jar"
                    )
                ]
            )
        ]
    )
}

pipeline {
    agent any
    tools {
        maven "Maven 3.8.4"
    }
    environment {
        SOURCE_CODE_URL = 'https://github.com/wanniDev/rolling.git'
        RELEASE_BRANCH = ''
        SERVER_LIST = 'was1'
    }
    stages {
        stage('clone') {
            steps {
                RELEASE_BRANCH = env.BRANCH_NAME
                git url: "$SOURCE_CODE_URL",
                    branch: "$RELEASE_BRANCH",
                sh "ls -al"
            }
        }
        stage('test') {
            when {
                changeRequest target: 'main'
            }
            steps {
                sh "pwd"
                sh "mvn clean test"
                echo "test"
            }
        }
        stage('build') {
            steps {
                echo "build"
            }
        }
        stage('deploy') {
            steps {
                echo "deploy"
                echo "${SERVER_LIST}"

                script {
                    echo "${SERVER_LIST}"
                    ssh_publisher("${SERVER_LIST}")
                }
            }
        }
    }
}