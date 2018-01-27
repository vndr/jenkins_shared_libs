#!/usr/bin/groovy
package org.unomee;
import org.unomee.*;


/*****
 pythonPipeline(pipelineDefinition['pipelineType']) {
 // Create a globally accessible variable that makes
 // the YAML pipeline definition available to all scripts
 pd = pipelineDefinition
 }
 ****/

def executePipeline(pipelineDefinition) {
    println("This is pipelineDefinition inside javaPipeline: " + pipelineDefinition)


    node {
        checkout scm

        stage('Build') {
            sh "./gradlew build"
        }

        if (pipelineDefinition['runTests']) {
            stage('Run Tests') {
                sh "pwd"
                sh "./gradlew -x check"
                println("Java test is running")
            }
        }

        if (pipelineDefinition['deployUponTestSuccess']) {
            stage('Deploy') {
//                sh "path/to/a/deploy/bash/script.sh ${pd.deploymentEnvironment}"
                sh "./gradlew run"
                println("Java App is deployed")

            }
        }
    }
}

return this