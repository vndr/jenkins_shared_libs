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
    println("This is pipelineDefinition inside nodeJSPipeline: " + pipelineDefinition)


    node {

        if (pipelineDefinition['runTests']) {
            stage('Run Tests') {
//                sh pd.testCommand
                println("NodeJS test is running")
            }
        }

        if (pipelineDefinition['deployUponTestSuccess']) {
            stage('Deploy') {
//                sh "path/to/a/deploy/bash/script.sh ${pd.deploymentEnvironment}"
                println("NodeJS App is deployed")

            }
        }
    }
}

return this