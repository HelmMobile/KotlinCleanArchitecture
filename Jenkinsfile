pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "./gradlew clean assembleDebug -PBUILD_NUMBER=${env.BUILD_NUMBER}"
            }
        }
        stage('Test') {
            steps {
                sh './gradlew check'
            }
        }
    }

    post {
        always {
            archive 'app/build/outputs/apk/*.apk'
            junit 'data/build/test-results/**/*.xml'
            androidLint canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
        }
    }
}
