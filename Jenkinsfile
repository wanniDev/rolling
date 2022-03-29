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
//         SOURCECODE_JENKINS_CREDENTIAL_ID = 'wanniDev'
        SOURCE_CODE_URL = 'https://github.com/wanniDev/rolling.git'
        RELEASE_BRANCH = 'main'
        SERVER_LIST = 'was1'
    }
    stages {
        stage('clone') {
            steps {
                git url: "$SOURCE_CODE_URL",
                    branch: "$RELEASE_BRANCH"
//                     credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
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
                echo "$TEST"
            }
        }
        stage('build') {
            when {
                changeRequest target: 'main'
            }
            steps {
                echo "build"
            }
        }

        stage('deploy') {
            when {
                branch 'main'
            }
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