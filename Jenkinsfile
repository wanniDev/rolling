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
                        execCommand: "pwd"
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
    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'main', name: 'CURRENT', type: 'PT_BRANCH'
    }
    stages {
        stage('clone') {
            steps {
                git url: "https://github.com/wanniDev/rolling.git",
                    branch: "${params.CURRENT}"
//                     credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
                sh "ls -al"
                sh "docker ps"
            }
        }
        stage('test') {
            steps {
                sh "pwd"
                sh "ls -al"
                sh "mvn clean test"
                echo "$TEST"
                echo "$TARGET"
                echo "CURRENT : ${params.CURRENT}"
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
                sh "ls -al"
                sh "ls -al ~"
                echo "build"
                sh "pwd"
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
                    stage('loadBalancer') {
                        sh "pwd"
                    }
                }
            }
        }
        stage('after') {
            steps {
                sh "pwd"
                sh "ls -la"
            }
        }
    }
}