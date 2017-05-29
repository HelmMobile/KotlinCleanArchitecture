
node {
  // Mark the code checkout 'stage'....
  stage 'Stage Checkout'

  // Checkout code from repository and update any submodules
  checkout scm

  sh 'git submodule update --init'

  stage 'Stage Build'

  //branch name from Jenkins environment variables
  echo "My branch is: ${env.BRANCH_NAME}"

  //build your gradle flavor, passes the current build number as a parameter to gradle
  sh "./gradlew clean assembleDebug -PBUILD_NUMBER=${env.BUILD_NUMBER}"


 stage 'Stage Test'

 sh "./gradlew test"

  stage 'Stage Archive'
  //tell Jenkins to archive the apks
  archiveArtifacts artifacts: 'app/build/outputs/apk/*.apk', fingerprint: true
  junit test: 'build/reports/**/*.html',  fingerprint: true
  //stage 'Stage Upload To Fabric'
  //sh "./gradlew crashlyticsUploadDistributionDebug  -PBUILD_NUMBER=${env.BUILD_NUMBER}"
}

