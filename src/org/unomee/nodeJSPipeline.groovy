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
        stage('Checkout scm') {
            checkout scm
        }

        stage('Build') {
            try {
                sh "npm install"
            } catch (e) {
                currentBuild.result = 'FAILURE'
            }
        }

        if (pipelineDefinition['runTests']) {
            stage(' Unit Tests') {
                try {
                    sh "${pipelineDefinition.testCommand}"
                    sh "npm run test-with-coverage"

                } catch (e) {
                    currentBuild.result = 'FAILURE'
                } finally {
                    publishHTML([
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'coverage',
                            reportFiles: 'index.html',
                            reportName: 'HTML Report',
                            reportTitles: 'RCov Report'])
                }
            }
        }

        if (pipelineDefinition['deployUponTestSuccess']) {
            stage('Publish') {
                try {
                    sh "npm publish"
                } catch (e) {
                    currentBuild.result = 'FAILURE'
                }
            }
        }
    }
}

return this