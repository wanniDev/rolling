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
        maven "Maven 3.6.3"
    }
    environment {
        SOURCECODE_JENKINS_CREDENTIAL_ID = 'wanniDev'
        SOURCE_CODE_URL = 'https://github.com/wanniDev/rolling/'
        RELEASE_BRANCH = 'main'
        SERVER_LIST = 'was1'
    }
    stages {
        stage('clone') {
            steps {
                echo "clone"
            }
        }
        stage('test') {
            steps {
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
            }
        }
    }
}