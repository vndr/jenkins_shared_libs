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

        if (pipelineDefinition['runTests']) {
            stage('Run Tests') {
                sh "./gradlew run"
                println("Java test is running")
            }
        }

        if (pipelineDefinition['deployUponTestSuccess']) {
            stage('Deploy') {
//                sh "path/to/a/deploy/bash/script.sh ${pd.deploymentEnvironment}"
                println("Java App is deployed")

            }
        }
    }
}

return this