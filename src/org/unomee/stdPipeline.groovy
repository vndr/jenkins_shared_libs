#!/bin/groovy
package org.unomee;

def execute() {

    node {

        stage('Initialize') {
            checkout scm
            echo 'Loading pipeline definition'
            Yaml parser = new Yaml()
            Map pipelineDefinition = parser.load(new File(pwd() + '/pipeline.yml').text)
        }

        switch(pipelineDefinition.pipelineType) {
            case 'python':
                // Instantiate and execute a Python pipeline
                new pythonPipeline(pipelineDefinition).executePipeline()
            case 'nodejs':
                // Instantiate and execute a NodeJS pipeline
                new nodeJSPipeline(pipelineDefinition).executePipeline()
        }

    }

}