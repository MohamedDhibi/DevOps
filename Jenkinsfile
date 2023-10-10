pipeline {
    agent any
    stages {
        stage("Checkout Code") {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'Eya']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/MohamedDhibi/DevOps.git']]])
            }
        }
        stage("Build and Test") {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}