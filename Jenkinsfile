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
                        sourceFiles: "target/*.jar",
                        execCommand: "echo hello"
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
//         SERVER_LIST = 'was1'
        OUTPUT = ''
    }
    stages {
        stage('clone') {
            steps {
                git url: "$SOURCE_CODE_URL",
                    branch: "${GIT_BRANCH}"
//                     credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
                sh "ls -al"
            }
        }
        stage('test') {
            steps {
                sh "pwd"
                sh "mvn clean test"
                echo "$TEST"
                echo "$TARGET"
                echo "BRANCH_NAME : " + env.BRANCH_NAME
                echo "GIT_BRANCH : " + env.GIT_BRANCH
                echo "GIT_BRANCH : ${GIT_BRANCH}"
                echo "GIT_LOCAL_BRANCH : " + env.GIT_LOCAL_BRANCH
            }
        }
        stage('build') {
            when {
                environment name: "TARGET", value: "main"
            }
            steps {
                echo "build"
            }
        }

        stage('deploy') {
            steps {
                echo "deploy"

                script {
                    def server_list = ["was1", "was2"]
                    for(server in server_list) {
                        stage(server) {
                            ssh_publisher(server)
                        }
                    }
                }
            }
        }
    }
}