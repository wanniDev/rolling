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
    stages {
        stage('clone') {
            steps {
//                 git url: "$SOURCE_CODE_URL",
//                     branch: "$RELEASE_BRANCH",
//                     credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
//                 sh "ls -al"
                cleanWs()
                GIT_BRANCH_NAME = sh(returnStdout: true, script: 'echo ${payload} | python3 -c \"import sys,json;print(json.load(sys.stdin,strict=False)[\'ref\'][11:])\"').trim()
                echo "arrive from ${GIT_BRANCH_NAME}"
            }
        }
        stage('test') {
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