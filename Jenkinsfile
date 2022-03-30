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
            steps {
                sh "pwd"
                sh "mvn clean test"
                echo "$TEST"
                echo "$TARGET"
                echo env.BRANCH_NAME
                echo env.GIT_BRANCH
                echo "${GIT_BRANCH}"
                echo env.GIT_LOCAL_BRANCH
                echo "${GIT_LOCAL_BRANCH}"
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
            when {
                environment name: "TARGET", value: "main"
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