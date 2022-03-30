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
        OUTPUT = ''
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
                script {
                    cleanWs()
                    OUTPUT = sh(returnStdout: true, script: 'echo ${payload} | python3 -c \"import sys,json;print(json.load(sys.stdin,strict=False)[\'ref\'][11:])\"').trim()
                    sh "pwd"
                    sh "mvn clean test"
                    echo "$TEST"
                    echo "$TARGET"
                    echo "BRANCH_NAME : " + env.BRANCH_NAME
                    echo "GIT_BRANCH : " + env.GIT_BRANCH
                    echo "GIT_BRANCH : ${GIT_BRANCH}"
                    echo "GIT_LOCAL_BRANCH : " + env.GIT_LOCAL_BRANCH
                    echo "GET_ECHO : ${OUTPUT}"
                }
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